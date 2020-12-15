import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var userManager: UserManager!
    var pushNotificationManager: PushNotificationManager!

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        //CrashIntegrationKt.crashInit(handler: CrashlyticsCrashHandler())
        
        // Init everything
        let userManager = UserManager()
        InjectorCenter.sharedInstance.put(userManager)
        
        let pushNotificationService = PushNotificationServiceiOSImpl()
        InjectorCenter.sharedInstance.put(pushNotificationService, as: PushNotificationService.self)
        
        let pushNotificationManager = PushNotificationManager(service: pushNotificationService, userManager: userManager)
        InjectorCenter.sharedInstance.put(pushNotificationManager)
        
        let httpClient = HttpClientFactory().defaultHttpClient()
        let messageRepository = MessageRepositoryImpl(client: httpClient)
        InjectorCenter.sharedInstance.put(httpClient)
        InjectorCenter.sharedInstance.put(messageRepository, as: MessageRepository.self)
        let messageRepositoryIos = MessageRepositoryIos(repository: messageRepository)
        InjectorCenter.sharedInstance.put(messageRepositoryIos)
        
        let messageManager = MessageManagerImpl()
        InjectorCenter.sharedInstance.put(messageManager, as: MessageManager.self)
        
        // Fake logged-in user
        userManager.loggedUser = User(userId: 42, fullname: "Chuck Norris")
        
        pushNotificationManager.onApplicationStartup()
        
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }


}

