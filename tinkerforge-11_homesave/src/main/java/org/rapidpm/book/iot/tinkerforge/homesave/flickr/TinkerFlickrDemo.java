package org.rapidpm.book.iot.tinkerforge.homesave.flickr;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.BrickletMotionDetector;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;
import org.rapidpm.module.se.commons.WaitForQ;

import java.io.IOException;
import java.time.Instant;

import static java.lang.System.*;

/**
 * Created by Sven Ruppert on 05.01.2015.
 */
public class TinkerFlickrDemo {
  private static final String host = "192.168.0.201";
  private static final int port = 4223;
  private static final int CALLBACK_PERIOD = 2_50;

  private static final IPConnection ipcon = new IPConnection();

  private static final String masterUID = "6Dct25";
  public static final String LIGHT_UID = "jy2";
  public static final String HUMIDITY_UID = "kfd";
  public static final String TEMPERATURE_UID = "dXj";
  public static final String BAROMETER_UID = "jY4";


  public static void main(String[] args) throws AlreadyConnectedException, IOException {

    ipcon.connect(host, port);
    ipcon.setAutoReconnect(true);

    final BrickletMotionDetector motionDectector = new BrickletMotionDetector("kgn", ipcon);
    motionDectector.addMotionDetectedListener(() -> {
      String pFileName = "pic_" + Instant.now();
      try {
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec("raspistill -o " + pFileName + ".jpg");
        pr.waitFor();
        out.println("ok pic done... " + pFileName);
      } catch (IOException
          | InterruptedException ex) {
        out.println("ex = " + ex);
      }
    });

    WaitForQ waitForQ = new WaitForQ();
    waitForQ.addShutDownAction(() -> {
          try {
            ipcon.disconnect();
          } catch (NotConnectedException ignored) { }
        }
    );
    waitForQ.addShutDownAction(() -> exit(0));
    waitForQ.waitForQ();
  }

}
