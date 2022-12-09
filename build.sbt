val Scala2 = "2.13.10"
val Scala3 = "3.2.1"

ThisBuild / tlBaseVersion := "0.1"
ThisBuild / organization := "io.github.ahjohannessen"
ThisBuild / organizationName := "Alex Henning Johannessen"
ThisBuild / startYear := Some(2022)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers +=
  tlGitHubDev("ahjohannessen", "Alex Henning Johannessen")

ThisBuild / crossScalaVersions := Seq(Scala2, Scala3)
ThisBuild / scalaVersion := Scala3
ThisBuild / githubWorkflowTargetBranches := Seq("main")
ThisBuild / tlSonatypeUseLegacyHost := false

lazy val root = tlCrossRootProject.aggregate(lib, specs)

lazy val specs = project
  .in(file("s4s-lib-specs"))
  .settings(
    moduleName := "s4s-lib-specs",
    autoScalaLibrary := false,
    libraryDependencies := Nil,
    crossPaths := false,
    crossVersion := CrossVersion.disabled,
    crossScalaVersions := Seq(Scala3),
    publishConfiguration :=
      publishConfiguration.value.withOverwrite(true),
    publishLocalConfiguration :=
      publishLocalConfiguration.value.withOverwrite(true),
    Compile / packageDoc / publishArtifact := false,
    Compile / packageSrc / publishArtifact := false,
    Compile / doc / sources := Seq.empty,
    Test / test := {}
  )

lazy val lib = crossProject(JVMPlatform, JSPlatform)
  .withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .enablePlugins(Smithy4sCodegenPlugin)
  .in(file("s4s-lib"))
  .settings(
    moduleName := "s4s-lib",
    Compile / smithy4sInputDirs ++= ((specs / Compile / unmanagedResourceDirectories).value ** "*.smithy").get,
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "2.9.0",
      "com.disneystreaming.smithy4s" %%% "smithy4s-core" % smithy4sVersion.value
    )
  )
