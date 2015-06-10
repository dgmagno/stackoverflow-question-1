package p

import sbt._
import sbt.Keys._
import play.sbt.PlayJava

object SbtPlugin extends AutoPlugin {

  object autoImport {
    object SbtPluginKeys { 
      val myClassloader = TaskKey[ClassLoader]("my-classloader", "custom loader")              
      val myTask = InputKey[Unit]("my-task", "custom task")     
    }
  }

  import autoImport.SbtPluginKeys._
  
  override def requires = PlayJava

  override def trigger = allRequirements

  override def projectSettings: Seq[Setting[_]] = inConfig(Compile)(scopedSettings)         

  def scopedSettings = Seq(
    myClassloader <<= getLoader,
    myTask <<= myTaskDef
  )

  private def myTaskDef = Def.inputTask {
    import play.api.inject.guice.GuiceApplicationBuilder
    import core.SomeFile

    val app = new GuiceApplicationBuilder()
    .in(new File("."))
    .in(myClassloader.value)
    .build

    val someFile = app.injector.instanceOf(classOf[SomeFile])
    
    app.stop

    println(someFile.outputSomething)
  }

  private def getLoader = Def.task {
    import classpath._    

    val deps = fullClasspath.value
    val classes = classDirectory.value

    ClasspathUtilities.toLoader(
      deps.map(_.data).map(_.getAbsoluteFile) :+ classes, 
      this.getClass.getClassLoader
    )
  }

}