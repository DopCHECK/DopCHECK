import os
import openai
openai.api_key = open("key.txt", "r").read().strip("\n")


completion = openai.ChatCompletion.create(
  organization='your_own_organization_key',
  model='gpt-4',
  messages=[
    {'role': 'system', 'content': 'You are a senior Android development engineer.'},
    # in_context learning 1
    {'role': 'user', 'content': 'Give me an example of a call to CallStateChanged() in android 9'},
    {'role': 'assistant', 'content':open("in_context_sample1.txt", "r").read().strip("\n")},
    # in_context learning 2
    {'role': 'user', 'content': 'Give me an example of a call to getAllCellInfo() in android 9'},
    {'role': 'assistant', 'content': open("in_context_sample2.txt", "r").read().strip("\n")},
    # in_context learning 3
    {'role': 'user', 'content': 'If app requires access to call logs or needs to process outgoing calls, user must explicitly request these permissions from the CALL_LOG permission group. Otherwise, a SecurityException occurs, according this sentence write a test case in Android 9'},
    {'role': 'assistant', 'content': open("in_context_sample3.txt", "r").read().strip("\n")},
    # in_context learning 4
    {'role': 'user', 'content': 'Call Currency.getDisplayName(null) in android 8 to see if it throws a NullPointerException'},
    {'role': 'assistant', 'content': open("in_context_sample4.txt", "r").read().strip("\n")},
    # in_context learning 5
    {'role': 'user', 'content': 'Invoking getSaveFormData() in android 8'},
    {'role': 'assistant', 'content':open("in_context_sample5.txt", "r").read().strip("\n")},
    {'role': 'user', 'content': 'Give me an example of a call to startDiscovery() in wifimanager in android 10, even though it is deprecated,'},
    # {'role': 'user', 'content': 'Give me an example of a call to onResults() under TelephonyScanManager.NetworkScanCallback class in android 10'},
    # {'role': 'user', 'content': 'Give me an example of a call to getAvailableNetworks() in android 10'},
    # {'role': 'user', 'content': 'Give me an example of a call to addNetwork(),updateNetwork(),removeNetwork(),reassociate(),enableNetwork(),disableNetwork(),reconnect(),disconnect() in android 10'},
    # {'role': 'user', 'content': 'give me an example of a call to getConfiguredNetworks() in android 10.'},
    # {'role': 'user', 'content': 'Give me an example of a call to getCameraCharacteristics(),need access the value of LENS_POSE_ROTATION fields'},
    # {'role': 'user', 'content': 'If your app targets Android 10 or higher, your app cannot read the serial number until the user has granted your app permission to access the USB device or accessory.according this sentence write a case in android 10'},
    # {'role': 'user', 'content': 'Unless your app is the default input method editor (IME) or is the app that currently has focus, your app cannot access clipboard data on Android 10 or higher.according this sentence write a case in android 10'},
    # {'role': 'user','content':'On devices that run Android 10 or higher, apps cannot access /proc/net, which includes information about a device\'s network state. Apps that need access to this information, such as VPNs, should use the NetworkStatsManager or ConnectivityManager class. according this sentence, write one case on android 10.'}
    # {'role': 'user', 'content': 'Obtain randomized MAC address: Device owner apps and profile owner apps can retrieve the randomized MAC address assigned to a specific network by calling getRandomizedMacAddress(); Obtain actual, factory MAC address: Device owner apps can retrieve a device\'s actual hardware MAC address by calling getWifiMacAddress(). according these two sentence write two cases of call getRandomizedMacAddress() and getWifiMacAddress()'},
    # {'role': 'user', 'content': 'By default, apps targeting Android 10 and higher are given scoped access into external storage, or scoped storage. Such apps can see the following types of files within an external storage device without needing to request any storage-related user permissions: Files in the app-specific directory, accessed using getExternalFilesDir().according this sentence, write a case that invoke getExternalFilesDir() in Android 10.'},
  ],
  temperature=0
)
# f = open('./test_case/android10/Some telephony, Bluetooth, Wi-Fi APIs require FINE location permission', 'a')
#
#
# gpt_string = completion['choices'][0]['message']['content']
# start = '```java'
# end = '```'
# gpt_string = gpt_string[gpt_string.find(start) + 1:gpt_string.rfind(end)]
# f.write(gpt_string)
print(completion['choices'][0]['message']['content'])
