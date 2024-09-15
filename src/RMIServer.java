import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            RemoteServer server = new RemoteServer();

            Naming.rebind("rmi://localhost/HelloService", server);

            System.out.println("RMI Server is running...");
        } catch (RemoteException | java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
