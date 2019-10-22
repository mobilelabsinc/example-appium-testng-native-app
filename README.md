# example-appium-testng-native-app
Example Appium project using TestNG to allow for parallel cross platform testing

## Prerequisites 

Upload both the Android and iOS PhoneLookup applications to your deviceConnect cart.

## Configuration of the sample

To execute these tests, you'll need to rename testng.xml.default to testng.xml. Once renamed, you'll need to fill in the following information:

```
<parameter name="gigafoxUrl" value="GIGAFOX URL" />
<parameter name="gigafoxUser" value="GIGAFOX USER" />
<parameter name="gigafoxKey" value="GIGAFOX API TOKEN" />
```

The GIGAFOX URL is the URL or IP address of your GigaFox system with /Appium appended to the end. EX: https://your.gigafox.url/Appium
The GIGAFOX USER is your GigaFox username, usually your email address
The GIGAFOX API TOKEN is your GigaFox api token, found on the managing your account page


To run tests in parallel against devices create and fill in the following for each device you want to test

```
<test name="PHONE NAME">
    <parameter name="deviceId" value="DEVICE UDID" />
    <parameter name="deviceOs" value="Android" />
    <classes>
        <class name="TestPhoneLookup"/>
    </classes>
</test>
```

The PHONE NAME is just the name of the device, it is used only to report the tests for this device
The DEVICE UDID is the vendor ID found in GigaFox. For iOS this is the UDID, for Android this is the Serial Number
The deviceOS must match the device type iOS or Android


## Test Execution

Run the tests with your IDEs test runner or:

`mvn test`


