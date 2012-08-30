import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play2-cloudfront"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      organization := "mchv",
      publishTo := Some(Resolver.file("file", file(Option(System.getProperty("repository.path")).getOrElse("/tmp"))))      
    )

}
