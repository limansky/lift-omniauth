name := "Omniauth"

organization := "net.liftmodules"

version := "0.13"

liftVersion <<= liftVersion ?? "2.5.1"

liftEdition <<= liftVersion apply { _.substring(0,3) }

name <<= (name, liftEdition) { (n, e) =>  n + "_" + e }

scalaVersion <<= scalaVersion ?? "2.9.1"  // This project's scala version is purposefully set at the lowest common denominator to ensure each version compiles.

crossScalaVersions := Seq("2.10.4", "2.9.2", "2.9.1-1", "2.9.1") // Excluding "2.11.1" since Lift 2.5.1 isn't built for it

resolvers += "CB Central Mirror" at "http://repo.cloudbees.com/content/groups/public"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies <++= liftVersion { v =>
  Seq("net.liftweb"   %% "lift-webkit"  % v  % "provided",
      "net.databinder" %% "dispatch-core" % "0.8.10",
      "net.databinder" %% "dispatch-http" % "0.8.10",
      "net.databinder" %% "dispatch-oauth" % "0.8.10",
      "net.databinder" %% "dispatch-http-json" % "0.8.10"
    )
}

//scalacOptions ++= Seq("-feature")

publishTo <<= version { _.endsWith("SNAPSHOT") match {
        case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
        case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
 }

credentials += Credentials( file("sonatype.credentials") )

credentials += Credentials( file("/private/liftmodules/sonatype.credentials") )
 
publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
        <url>https://github.com/ghostm/lift-omniauth</url>
        <licenses>
            <license>
              <name>Apache 2.0 License</name>
              <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
              <distribution>repo</distribution>
            </license>
         </licenses>
         <scm>
            <url>git@github.com:ghostm/lift-omniauth.git</url>
            <connection>scm:git:git@github.com:ghostm/lift-omniauth.git</connection>
         </scm>
         <developers>
            <developer>
              <id>ghostm</id>
              <name>Matthew Henderson</name>
              <url>https://github.com/ghostm</url>
            </developer>
         </developers>
 )
