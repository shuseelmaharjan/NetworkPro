import java.net.*;
import java.util.Enumeration;

public class NetworkInterfaceE {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                System.out.println("Interface Name: " + networkInterface.getName());
                System.out.println("Display Name: " + networkInterface.getDisplayName());

                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    System.out.print("MAC Address: ");
                    for (int i = 0; i < mac.length; i++) {
                        System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                    }
                    System.out.println();
                }

                System.out.println("Is Interface Up: " + networkInterface.isUp());

                System.out.println("Supports Multicast: " + networkInterface.supportsMulticast());

                System.out.println("MTU: " + networkInterface.getMTU());

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println("IP Address: " + inetAddress.getHostAddress());
                }

            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
