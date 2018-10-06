# Introduction

This OSGi project extends the Liferay Login functionality to add 2 Factor Authentication (2FA) to Liferay DXP 7.1, with QR Code support. See Liferay Blog post [Adding 2FA to Liferay DXP 7.1](https://community.liferay.com/blogs/-/blogs/adding-2fa-to-liferay-dxp-7-1) for screenshots.

The Google Authenticator app (available for iPhone and Android) or other 2FA apps can be used by the user during login to generate a one-time passcode, with QR Code support included to populate the 2FA app user profile.

The project adds an 'Authenticator Code' to the Liferay Login screen with a Portlet Filter component and verifies the Authentication Code entered by the user using an auth.pipeline.post Authenticator component.

It uses the Time-based One-Time Password algorithm (TOTP), which computes a one-time password using a user specific shared secret key and the current time.

# Prerequisites

1. The deployment steps assume that outbound email is setup in Server Administration > Mail e.g. using [FakeSMTP](http://nilhcem.com/FakeSMTP/)
2. Access to an iOS or Android OS device to download and run Google Authenticator app or other 2FA app

# Deployment & Setup

The following steps cover building, deploying, configuring and testing:

1. The GitHub Repository is a Liferay Workspace. Clone or download the Repository and import into an Eclipse Workspace as a Liferay Workspace
2. Perform a Gradle > Refresh Gradle Project on all the modules then build the totp-2fa OSGi bundles with Gradle (through the Gradle Tasks view)
3. Copy the following newly built totp-2fa OSGi bundles to the Liferay deploy folder and confirm they deploy successfully with the Gogo shell:
* com.mw.totp-2fa.login.auth
* com.mw.totp-2fa.service
* com.mw.totp_2fa.key.api
* com.mw.totp_2fa.key.service
* com.mw.totp-2fa.qrcode
* com.mw.totp-2fa.user.model.listener
4. Login to Liferay as an Administrator
5. Go to Control Panel > Configuration > System Settings > Security > TOTP 2FA
6. Enable the 'Login TOTP 2FA Enabled' checkbox
7. Ensure the 'QR Code URL' and 'QR Code Resend URL' settings are correct for the environment, update if needed
8. Update 'Application Name' / 'QR Code Issuer Name' / 'QR Code Email From' / 'QR Code Email From Label' if needed
9. Click 'Save' to save and apply the changes
10. Logout of Liferay
11. Go to the Home page and click 'Login'. Confirm that the Authenticator Code field appears below the Password field
12. Download the Google Authenticator app to your phone and launch it
13. Go to Control Panel > Users
14. For each user you wish to test with:
* Select the user, go to General > Password screen
* Click 'Generate 2FA Secret Key and email QR Code URL' to generate a Secret Key and display a QR Code onscreen
* In Google Authenticator app click the '+' icon, select 'Scan barcode' and scan the onscreen QR Code to add the user to Google Authenticator app. (Google Authenticator app will need access to your camera.)

**Note: Users created after the full set of application modules are deployed will automatically be assigned a Secret Key on account creation and will be emailed a link to the QR Code.**

# Notes

1. Supported Liferay versions: **DXP 7.1**
2. 2 Factor Authentication Login must be explicitly configured and enabled after initial deployment, see 'Deployment & Setup Steps' to enable
3. The user specific Secret Keys are stored in plain text in the totp_secretkey table (created by Service Builder)
4. Ensure that the phone and server time are roughly the same, if not then the generated codes may not match when the comparison is done, as the code is only valid for 30 / 60 seconds. See System Settings > TOTP 2FA > Advanced > Allow for Time Skew below to make the verification more lenient
5. If the Google Authenticator code is red it means it is about to expire. If Allow for Time Skew is off then wait until a new one is generated before trying as a time difference of a few seconds between the phone and server means it may not work
6. Users with the Liferay Administrator role will always bypass TOTP 2FA on login and can leave the Authenticator Code field empty
7. A bug in the Liferay DXP 7.1 Senna / Single Page Application (SPA) implementation unexpectedly prevents the Login Modal Dialog form body fields being included in the parameterMap passed through to the Authenticator. Senna is turned off for the Login form / portlet through the login.jsp fragment to ensure this doesn't prevent the Authenticator functioning as expected
8. The project uses an auth.pipeline.post Authenticator. If the regular credentials are not valid then the custom Authenticator will not be triggered
9. QR Code URLs send by email are valid for 60 minutes by default. The user can request a new QR Code URL to be sent to their email address. The validity duration can be changed in Control Panel > Configuration > System Settings > Security > TOTP 2FA > QR Code URL Duration
10. You can bypass 2FA checks for non-Administrator users by defining a Regular User Role, adding the users to the role and setting the System Settings > TOTP 2FA > Login TOTP 2FA Skip User Role setting

# OSGi Bundles

The project consists of the following OSGi bundles:

com.mw.totp-2fa.login.auth
- Contains a Login portlet PortletFilter component that adds the Authenticator Code field to the Login screen and adds data-senna-off="true" to the Login form html tag
- Contains an auth.pipeline.post Authenticator component that extracts the Authenticator Code from the Login form parameters and verifies it matches one generated based on the users secretKey and the current time

com.mw.totp-2fa.service
- Contains the System Settings > Security > TOTP 2FA Configuration component
- Contains the TOTP Generator Interface and Implementation component
- Contains the en_US Resource Bundle component and corresponding resource bundle

com.mw.totp_2fa.key.api / com.mw.totp_2fa.key.service
- Contains the Service Builder api and service for the Secret Key entity

com.mw.totp-2fa.qrcode
- Contains a Servlet component used to generate / render QR Codes
- Contains Portlet Filter components to include the QR Codes & Secret Keys and Generate / Regenerate actions on the User Profile screens (Password tab)
- Contains QR Code service component used to send emails with the QR Code URLs

com.mw.totp-2fa.user.model.listener
- Contains a User Model Listener component with afterCreate method to add a Secret Key when a new user is created and email a QR Code URL link to the user

com.mw.totp-2fa.activator
- Contains a BundleActivator component that will generate Secret Keys for all active users that don't already have one and email a link to the QR Code to the user

# 2 Factor Authentication App setup

**Use of the QR Code when adding a profile is strongly recommended.**

A number of 2 Factor Authentication Apps are available in the Apple and Google App Stores if you choose not to use the Google Authenticator app, for example:
* 2FA - 2-Factor Authentication
* FreeOTP Authenticator

If you choose an alternative to the Google Authenticator app or are manually adding the entry rather than using a QR Code in the 2FA app, please note the following:

* If prompted for type, select 'Time-based' rather than 'Counter-based'
* If prompted for interval, select 30 seconds (or the System Settings > TOTP 2FA > Advanced > Authenticator Code Duration if different)
* If prompted for length, select 6 digits (or the System Settings > TOTP 2FA > Advanced > Authenticator Code Length if different)
* If prompted for algorithm, select SHA-1

NOTE: Google Authenticator QR Code setup will apply the Authenticator Code Duration and Authenticator Code Length values correctly, but these fields are (currently) NOT editable with the Manual entry setup, so the manually created profile will use 30 and 6 respectively so may not function as expected.

# System Settings > TOTP 2FA > Advanced Settings

Advanced > Allow for Time Skew
- Default is false.
- If enabled it allows 2FA Authentication Code match based on the previous, current or next Authentication Code based on current timestamp minus or plus the Authenticator Code Duration seconds.

Advanced > Authenticator Code Length
- Default is 6.
- Sets the length of the code generated by 2FA apps when the profile is created from a QR Code URL. Also used for validation.
- **NOTE: Changing this setting will invalidate all pre-existing 2FA app profiles and previously issued QR Code URLs.**
- **NOTE: j256 two-factor-auth TOTP implementation expects 'Authenticator Code Length' to be 6, if not 6, it will only check the last 6 digits.**

Advanced > Authenticator Code Duration
- Default is 60.
- Sets the duration (in seconds) of the code generated by 2FA apps when the profile is created from a QR Code URL.
- **NOTE: Changing this setting will invalidate all pre-existing 2FA app profiles and previously issued QR Code URLs.**

Advanced > TOTP 2FA Implementation
- Default is java-opt.
- The specific implementation to use when generating the Authenticator Code for comparison.
- **NOTE: j256 two-factor-auth TOTP implementation requires 'Authenticator Code Length' to be 6, if not 6, it will only check the last 6 digits.**

# TOTP Implementation

Time-based One-Time Password algorithm (TOTP) is an algorithm that computes a one-time password using a user specific shared secret key and the current time.

TOTP is widely used in two-factor authentication systems, for more info see:

* [Wikipedia - Time-based One-Time Password algorithm](https://en.wikipedia.org/wiki/Time-based_One-time_Password_algorithm)
* [RFC 6238 - TOTP: Time-Based One-Time Password Algorithm](https://tools.ietf.org/html/rfc6238)

This project uses the following TOTP implementation(s):

* [java-opt](https://github.com/jchambers/java-otp/) available here: [Maven.org - java-opt](https://search.maven.org/search?q=a:java-otp)
* [J256 two-factor-auth](https://github.com/j256/two-factor-auth) available here: [MVN Repository - J256 two-factor-auth](https://mvnrepository.com/artifact/com.j256.two-factor-auth/two-factor-auth)

The default implementation is java-otp, this can be switched through System Settings > Security > TOTP 2FA > TOTP 2FA Implementation.

# Supported Locales

An OSGi Resource Bundle service component is defined for en_US, mapped to TOTP_2FALanguage.properties. The subject and body of the QR Code URL email come from the resource bundle based on each users Language / Locale setting. If you environment supports other Languages / Locales you can should create additional OSGi Resource Bundle service components and resource bundles, see [Liferay - Overriding Global Language Keys](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/overriding-global-language-keys) for more info.<br/>**If the translations for a users Language / Locale are not available, the system will revert to en_US when generating the QR Code URL email.**

# Limitations

1. The user specific Secret Keys are stored in plain text in the Liferay database
2. The Authentication Code is shown on the same screen as the Username and Password rather than on a subsequent screen
3. More complex authentication scenarios such as SSO / Oauth etc. are not supported
4. Liferay Screens not (currently) supported
5. System Settings > TOTP 2 FA scope is set to System so the settings apply to all Liferay instances