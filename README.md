# Cloudfront module for play 2

Cloudfront enables to set up a CDN easily. This module helps to integrate your Cloudfront CDN with play.

## Setting up with sbt

Configure a new resolver:

```scala
resolvers += "Mariot Chauvin" at "http://mchv.me/repository"
```

Add the library dependency:

```scala
libraryDependencies += "mchv" %% "play2-cloudfront" % "1.0"
```

## Use a custom controller

Create a new file named `RemoteAssets.scala` that contains:

```scala
package controllers

import controllers._

object RemoteAssets extends Remote {
    def call(file:String) = {
        controllers.routes.RemoteAssets.at(file)
    }
}
```

Update the router file with the call to your custom controller for your assets:

```properties
# Map static resources from the /public folder to the /assets URL path
GET     /assets/*file               controllers.RemoteAssets.at(path="/public", file)
```

Update your views to refer to your controller to fetch the url:

```html
@(title: String)(content: Html)

<!DOCTYPE html>

<html>
    <head>
        <title>@title</title>
        <link rel="stylesheet" media="screen" href="@RemoteAssets.url("stylesheets/main.css")">
        <link rel="shortcut icon" type="image/png" href="@RemoteAssets.url("images/favicon.png")">
        <script src="@RemoteAssets.url("javascripts/jquery-1.7.1.min.js")" type="text/javascript"></script>
    </head>
    <body>
        @content
    </body>
</html>
```


## Configure the module with your cloudfront settings

Add your Cloudfront url to the `application.conf`:

```properties
cdn-url="http://d7471vfo50fqt.cloudfront.net"
```

## More

The module is based on [James Ward tutorial](http://www.jamesward.com/2012/08/08/edge-caching-with-play2-heroku-cloudfront.)
If you want to know more or avoid the dependency, please read it.