**************************************
Intro
**************************************

This project adds 2 Factor Authentication to Liferay DXP 7.1 with QR Code support.

It uses the Time-based One-Time Password algorithm (TOTP), which computes a one-time password using a user specific shared secret key and the current time. TOTP is widely used in two-factor authentication systems.

The Google Authenticator app (available for iPhone and Android) or other 2FA apps can be used by the user during login to generate a one-time password.

The project adds an 'Authentication Code' field to the Liferay Login screen below the Password field and verifies the 'Authentication Code' entered by the user as part of the Login workflow.

QR Codes are used to configure the users account in the 2FA app.

**************************************
Deployment & Setup
**************************************

The following steps cover building, deploying, configuring and testing the project:

1. The GitHub Repository is a Liferay Workspace. Clone or download the Repository and import into an Eclipse Workspace as a Liferay Workspace

2. Perform a Gradle > Refresh Gradle Project then build the totp-2fa OSGi bundles with Gradle through the Gradle Tasks view

3. Copy the following newly built totp-2fa OSGi bundles to the Liferay deploy folder and confirm they deploy successfully with the Gogo shell
- com.mw.totp-2fa.login.fragment (this is a Fragment bundle so it will remain at Resolved status)
- com.mw.totp-2fa.login.auth
- com.mw.totp-2fa.service
- com.mw.totp_2fa.key.api
- com.mw.totp_2fa.key.service
- com.mw.totp-2fa.qrcode
- com.mw.totp-2fa.user.model.listener

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

- Select the user, go to General > Password screen
- Click 'Generate Secret Key and email QR Code' to generate a Secret Key and display a QR Code onscreen
- In Google Authenticator app click the '+' icon, select 'Scan barcode' and scan the onscreen QR Code to add the user to Google Authenticator app. (Google Authenticator app will need access to your camera.)

Note: Users created after the full set of application modules are deployed will automatically be assigned a Secret Key on account creation and will be emailed a link to the QR Code.

**************************************
Notes
**************************************

i. Supported Liferay versions: DXP 7.1
ii. 2 Factor Authentication Login must be explicitly configured and enabled after initial deployment, see 'Deployment & Setup Steps' below
iii. The Secret Keys are stored in plain text in the totp_secretkey table (created by Service Builder)
iv. Ensure that the phone and server time are roughly the same, if not then the generated codes may not match when the comparison is done, as the code is only valid for 30 seconds
v. If the Google Authenticator code is red it means it is about to expire
- Wait until a new one is generated before trying as a time difference of a few seconds between the phone and server means it may not work
vi. Users with the Liferay Administrator role will always bypass TOTP 2FA on login and can leave the Authenticator Code field empty
vii. A bug in the Liferay DXP 7.1 Senna / Single Page Application (SPA) implementation unexpectedly prevents the Login Modal Dialog form body fields being included in the parameterMap passed through to the Authenticator
- Senna is turned off for the Login form / portlet through the login.jsp fragment to ensure this doesn't prevent with the Authenticator functioning as expected
iix. The project uses an auth.pipeline.post Authenticator. If the regular credentials are not valid then the custom Authenticator will not be triggered
ix. If you change company.security.auth.type (either through portal-ext.properties or the GUI then you will need to update the System Settings > Security > TOTP 2FA > Temporary Secret Key Mappings to use the new identifier for each user

**************************************
OSGi Bundles
**************************************

The project consists of the following OSGi bundles:

com.mw.totp-2fa.login.fragment
- Contains a fragment to overide Login Portlet login.jsp
- The fragment disables Senna for the login portlet (by adding data-senna-off="true" to the aui:form tag)
- The fragment adds a new dynamic include with key="com.liferay.login.web#/login.jsp#loginFieldsPost" below the Password field

com.mw.totp-2fa.login.auth
- Contains the Dynamic Include that adds the Authenticator Code field to the Login screen
- Contains an auth.pipeline.post Authenticator that extracts the Authenticator Code from the Login form and verifies it matches one generated based on the users secretKey and the current time

com.mw.totp-2fa.service
- Contains the System Settings > Security > TOTO 2FA Configuration
- Contains the TOTP Generator Interface and Implementations

com.mw.totp_2fa.key.api / com.mw.totp_2fa.key.service
- Contains the Service Builder service for Secret Key entity

com.mw.totp-2fa.qrcode
- Contains the servlet used to generate / render the QR Codes
- Contains Portlet Filters to include the QR Codes / Secret Keys on the User Profile screens (password tab)
- Contains service used to send email with the QR Code links

com.mw.totp-2fa.user.model.listener
- Contains a User Model Listener with afterCreate method to add a Secret Key when a new user is created and email a link to the QR Code to the user

com.mw.totp-2fa.activator
- Contains a BundleActivator that will generate Secret Keys for all users that don't have one and email a link to the QR Code to the user

**************************************
2 Factor Authentication App setup
**************************************

There are lots of 2 Factor Authentication Apps available in the Apple and Google App Stores (e.g. 2FA - 2-Factor Authentication or FreeOTP Authenticator). 

If you choose an alternative to Google Authenticator app or are manually adding the entry rather than using a QR Code, please note the following:

i. If prompted for type, select 'Time-based' rather than 'Counter-based'
ii. If prompted for interval, select 30 seconds
iii. If prompted for length, select 6 digits
iv. If prompted for algorithm, select SHA-1

**************************************
TOTP Implementation
**************************************

Time-based One-Time Password algorithm (TOTP) is an algorithm that computes a one-time password using a user specific shared secret key and the current time.

For more info see:

- https://en.wikipedia.org/wiki/Time-based_One-time_Password_algorithm
- https://tools.ietf.org/html/rfc6238

This project uses the following TOTP implementation(s):

- https://github.com/jchambers/java-otp/ available here: https://search.maven.org/search?q=a:java-otp
- https://github.com/j256/two-factor-auth available here: https://mvnrepository.com/artifact/com.j256.two-factor-auth/two-factor-auth

The default implementation is java-otp, this can be switched through System Settings > Security > TOTP 2FA > TOTP 2FA Implementation.