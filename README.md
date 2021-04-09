# FoodOn

## Installation Guide
1. Clone repository
2. Open the Project in your android studio
3. IMPORTANT:- Change the [PackageName](https://stackoverflow.com/questions/16804093/android-studio-rename-package)
4. Create Firebase [project](https://console.firebase.google.com/).
5. [Connect](https://youtu.be/ggMPCD9hlaQ) Firebase with Android Studio.
6. Download the file google-service.json from firebase project setting and import into your project
7. IMPORTANT FOR NOTIFICATION:- Copy Server key from Firebase/Project Setting/cloudmessaging/ and paste it in APIService.java class

## Alert
If you will not connect with your firebase and try to run this app with mine firebase connection than you will not see any dish etc. Because I have restricted the rules to read and write

### Corection in Delivery Panel
After Registering as delivery person , copy UID of that delivery person from firebase and in : "FoodOn_FoodDelivery_by_Androrealm/app/src/main/java/com/food_on/app/ChefFoodPanel/ChefPreparedOrderView.java" file
at line 65=> String deliveryId = "oCpc4SwLVFbKO0fPdtp4R6bmDmI3"; paste here.
Similarly, Do this in DeliveryPendingOrderFragment.java file


## Connect with me:
[<img align="left" alt="androrealm | YouTube" width="30px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/youtube.svg" />](https://rb.gy/r4yzzi)
[<img align="left" alt="dshakir52 | Instagram" width="30px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/instagram.svg" />](https://www.instagram.com/dshakir52/).
