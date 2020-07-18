package com.reactnativezendesk

import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import zendesk.chat.Chat
import zendesk.chat.ChatConfiguration
import zendesk.chat.ChatEngine
import zendesk.configurations.Configuration
import zendesk.core.AnonymousIdentity
import zendesk.core.Zendesk
import zendesk.messaging.MessagingActivity
import zendesk.support.Support
import zendesk.support.guide.HelpCenterActivity
import zendesk.support.guide.ViewArticleActivity
import zendesk.support.request.RequestActivity
import zendesk.support.requestlist.RequestListActivity


class ZendeskModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "Zendesk"
  }

  @ReactMethod
  fun setup(zendeskUrl: String, applicationId: String, oauthClientId: String, promise: Promise){
    try {
      Zendesk.INSTANCE.init(reactApplicationContext, zendeskUrl, applicationId, oauthClientId)
      Support.INSTANCE.init(Zendesk.INSTANCE)
      Chat.INSTANCE.init(reactApplicationContext, "61gICS9lKk794W3pYI3AtbuckG3FDKeS")
      promise.resolve(true)
    }catch (e: Exception){
      promise.reject("error", "Could not configure");
    }
  }

  @ReactMethod
  fun setupChat(accountKey: String, promise: Promise){
    try {
      Chat.INSTANCE.init(reactApplicationContext, accountKey)
      promise.resolve(true)
    }catch (e: Exception){
      promise.reject("error", "Could not configure");
    }
  }

  @ReactMethod
  fun setIdentity(name: String, email:String, promise: Promise){
    try {
      val identity = AnonymousIdentity.Builder()
        .withNameIdentifier(name)
        .withEmailIdentifier(email)
        .build()
      Zendesk.INSTANCE.setIdentity(identity)

      promise.resolve(true)
    }
    catch (e: Exception) {
      Log.d("Ocorreu um erro", e.message)
      promise.reject("error", "could not configure");
    }

  }

  @ReactMethod
  fun showHelpCenter(contactUsButtonVisible: Boolean, promise: Promise ){
    try {
      val intent: Intent = HelpCenterActivity.builder()
        .withContactUsButtonVisible(contactUsButtonVisible)
        .intent(reactApplicationContext)

      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

      reactApplicationContext.startActivity(intent);
      promise.resolve(true)
    }catch (e: Exception){
      promise.reject("error","Could not open Help Center")
    }
  }

  @ReactMethod
  fun viewArticleId(id: Long,promise: Promise){
    try {
      val intent: Intent = ViewArticleActivity.builder(id)
        .intent(reactApplicationContext)

      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

      reactApplicationContext.startActivity(intent);
      promise.resolve(true)
    }catch (e: Exception){
      promise.reject("error","Could not open Article")
    }
  }

  @ReactMethod
  fun showRequests(subject: String,promise: Promise){
    try {
      val requestActivityConfig: Configuration = RequestActivity.builder()
        .withTags("android", "mobile")
        .config()


      val intent: Intent = RequestListActivity.builder()
        .intent(reactApplicationContext, requestActivityConfig)

      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

      reactApplicationContext.startActivity(intent);


      promise.resolve(true)
    }catch (e: Exception){
      promise.reject("error","Could not open Article")
    }
  }

  @ReactMethod
  fun showRequest(subject: String,promise: Promise){
    try {
      val requestActivityConfig: Configuration = RequestActivity.builder()
        .withTags("android", "mobile")
        .config()


      val intent: Intent = RequestActivity.builder()
        .intent(reactApplicationContext, requestActivityConfig)

      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

      reactApplicationContext.startActivity(intent);


      promise.resolve(true)
    }catch (e: Exception){
      promise.reject("error","Could not open Article")
    }
  }

  @ReactMethod
  fun showChat(promise: Promise){
    val chatConfiguration = ChatConfiguration.builder()
      .withAgentAvailabilityEnabled(false)
      .build()

    val intent: Intent = MessagingActivity.builder()
      .withEngines(ChatEngine.engine())
      .intent(reactApplicationContext, chatConfiguration)

    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    reactApplicationContext.startActivity(intent);

    promise.resolve(true);
  }

  @ReactMethod
  fun multiply(a: Int, b: Int, promise: Promise) {

    Zendesk.INSTANCE.setIdentity(AnonymousIdentity())

    val sharingIntent = Intent(Intent.ACTION_VIEW)
    sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

//      val teste = RequestActivity.builder()
//        .intent(reactApplicationContext)

    val teste = HelpCenterActivity.builder()
      .withContactUsButtonVisible(false)
      .intent(reactApplicationContext)

    teste.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    reactApplicationContext.startActivity(teste)
    promise.resolve(a * b)

  }


}
