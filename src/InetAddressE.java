import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressE {

    public static void main(String[] args) {
        try {
            InetAddress google = InetAddress.getByName("google.com");
            System.out.println("Google Hostname: " + google.getHostName());
            System.out.println("Google IP Address: " + google.getHostAddress());
            System.out.println();

            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Local Hostname: " + localHost.getHostName());
            System.out.println("Local IP Address: " + localHost.getHostAddress());
            System.out.println();

            InetAddress[] googleAddresses = InetAddress.getAllByName("google.com");
            System.out.println("All Google IP Addresses:");
            for (InetAddress address : googleAddresses) {
                System.out.println(address);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
