# speech-to-text-demo

This is a demo android application developed to test REAL-TIME various speech-to-text third part solution available for Android Devices.
Here we can included 3 apis:
	1)android.speech - Available in android devices offline. Its supports varied range of languages. More than 100+ languages.
	2)google.cloud - This API is provided by google. This works online and supports 100+ languages.
	3)livai.speech - This is developed by Liv.ai(an indian banaglore based startup). This supports 8+ indian laguages including Hindi and English.

However, In this demo, I have focused on developing real time speech-to-text for Hindi language.

## Pre-requists to use
1)android.speech - Its ready to use.
2)google.cloud - To use this, you have to avail Google Speech API. You will receive an credentials.json when you Enable the api in Google API Console. Follow these steps to generate credentails.json file:
	i)Checkout this link: https://console.cloud.google.com 
	ii)Create a project
	iii)Enable Google Speech API service
	iv)Generate credential.json
3)livai.speech - Its ready to use. However, there is a passkey required to use this for commercial use. I have take passkey from their demo repo available on Github.

##Insights drawn from Analysis
1) Google.cloud provides best results among all for real-time STT Hindi languages. Followed by liv.ai then android.speech.
2) You get audio files if you use google.cloud. This is not possible(easy way) in other two implementation.
3) Google.cloud don't provide SDK specifically for Android so far. So, its most difficult to implement among all.

## References
[1] https://cloud.google.com/speech-to-text/docs/reference/libraries
[2] https://github.com/GoogleCloudPlatform/android-docs-samples/tree/master/speech/Speech
[3] https://github.com/livai/android_speech_api/tree/api_service
and many more. 
