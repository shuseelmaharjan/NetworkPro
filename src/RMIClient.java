import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try {
            RemoteInterface remoteObject = (RemoteInterface) Naming.lookup("rmi://localhost/HelloService");

            String response = remoteObject.sayHello();
            System.out.println("Response from server: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
