package controllers

import play.api.mvc._
import play.api.Play
import play.api.Play.current
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.DateTimeZone
import java.util.Date
import scala.Some


trait Remote extends Controller {

  private val timeZoneCode = "GMT"

  private val df: DateTimeFormatter =
    DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss '"+timeZoneCode+"'").withLocale(java.util.Locale.ENGLISH).withZone(DateTimeZone.forID(timeZoneCode))

  type ResultWithHeaders = SimpleResult { def withHeaders(headers: (String, String)*): SimpleResult }

  def at(path: String, file: String): Action[AnyContent] = Action { request =>
    val action = Assets.at(path, file)
    val result = action.apply(request)
    val resultWithHeaders = result.asInstanceOf[ResultWithHeaders]
    resultWithHeaders.withHeaders(DATE -> df.print((new Date).getTime))
  }

  def url(file: String) = {
    Play.configuration.getString("cdn-url") match {
      case Some(contentUrl) => contentUrl + call(file).url
      case None => call(file)
    }

  }

  def call(file: String): Call
}
