package com.oskm.apns;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import org.apache.log4j.Logger;

public class APNSMessageSender {

    private static final Logger LOG = Logger.getLogger(APNSMessageSender.class);

    public void send() {
        try {
            Push.alert("Hello World!", "D:\\_dev\\workspace\\shs-smg-batch_trunk\\src\\java\\com\\sec\\pcw\\smg\\apns\\certificate\\smarthomelauncher-dev.p12", "samsung", false, "3fbf5d36e9c8ce61a7734f27b9a6633c8c2f1bfeb4a754147c1653cdc2ac2dbb");

        } catch (CommunicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeystoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*
	public void send() {

		PushNotificationManager pushManager = PushNotificationManager.getInstance();

		int port = 2195;
		// LOG.debug("requestToAPNS: "+registrationId);

		if (LOG.isInfoEnabled()) {
			LOG.info("[SMG][requestToAPNS] registraionId: " + registrationId);
			LOG.info("[SMG][requestToAPNS] PayLoad: " + postData.toString());
		}
		try {
			UUID uid = UUID.randomUUID();

			pushManager.initializeConnection(apnsConfig.getSendTestUrl(), port, apnsConfig.getApnsCtfPath(),
					apnsConfig.getApnsCtfPwd(), SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			pushManager.addDevice(uid.toString(), registrationId);
			Device client = pushManager.getDevice(uid.toString());
			pushManager.sendNotification(client, postData);
			pushManager.stopConnection();
			pushManager.removeDevice(uid.toString());
			responseCode = HttpStatus.SC_OK;
			// PushNotificationManager.getInstance().getDevice(registrationId).getToken();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info(e, e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("[SMG][requestToAPNS] responseCode: " + responseCode);
		}

	}
	*/

}
