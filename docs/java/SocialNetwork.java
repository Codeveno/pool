import java.util.*;

@SuppressWarnings("unused")
public class SocialNetwork {

    public static void main(String[] args) {
        String network = "";

        // Start with an empty network; print it out.
        System.out.println(network);

        // Add three users, swofferh, achen47 and hibbahk, in the correct order and with the friends listed above.
        network = addUser(network, "swofferh", "achen47-hibbahk");
        network = addUser(network, "achen47", "swofferh-hibbahk");
        network = addUser(network, "hibbahk", "swofferh-achen47");

        // Print out the network and the network's size.
        System.out.println(network);
        System.out.println("Network size: " + networkSize(network));

        // Remove the user achen47.
        network = removeUser(network, "achen47");

        // Print out the network and the network's size.
        System.out.println(network);
        System.out.println("Network size: " + networkSize(network));

        // Add the user juliak24, with the friends listed above.
        network = addUser(network, "juliak24", "swofferh-hibbahk");

        // Print out the network and the network's size.
        System.out.println(network);
        System.out.println("Network size: " + networkSize(network));

        // Add the user meravf, with the friends listed above.
        network = addUser(network, "meravf", "swofferh-hibbahk-juliak24");

        // Print out the network and the network's size.
        System.out.println(network);
        System.out.println("Network size: " + networkSize(network));

        // Add the user mxw, with no friends.
        network = addUser(network, "mxw", "");

        // Print out the network and the network's size.
        System.out.println(network);
        System.out.println("Network size: " + networkSize(network));

        // Visualize the network
        visualizeNetwork(network);
    }

    // Method to add a user to the network
    public static String addUser(String network, String user, String friends) {
        return network + user + ":" + friends + ",";
    }

    // Method to count the number of users in the network
    public static int networkSize(String network) {
        int count = 0;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ':') {
                count++;
            }
        }
        return count;
    }

    // Method to remove a user from the network
    public static String removeUser(String network, String user) {
        // Remove the user-friends pair for the specified user
        int startIndex = network.indexOf(user + ":");
        if (startIndex == -1) {
            return network; // User not found
        }
        int endIndex = network.indexOf(",", startIndex);
        if (endIndex == -1) {
            return network.substring(0, startIndex);
        }
        String updatedNetwork = network.substring(0, startIndex) + network.substring(endIndex + 1);

        // Remove references to the deleted user from other users' friends lists
        String userToRemove = user + "-";
        String userToRemoveEnd = "-" + user;
        while (updatedNetwork.contains(userToRemove) || updatedNetwork.contains(userToRemoveEnd)) {
            updatedNetwork = updatedNetwork.replace(userToRemove, "").replace(userToRemoveEnd, "");
        }

        return updatedNetwork;
    }

    // Method to visualize the network
    public static void visualizeNetwork(String network) {
        int startIndex = 0;
        while (startIndex < network.length()) {
            int colonIndex = network.indexOf(":", startIndex);
            if (colonIndex == -1) {
                break;
            }
            String user = network.substring(startIndex, colonIndex);
            System.out.println(user);

            int commaIndex = network.indexOf(",", colonIndex);
            String friends = network.substring(colonIndex + 1, commaIndex);
            if (!friends.isEmpty()) {
                System.out.println(friends.replace("-", "\n"));
            }
            System.out.println();
            startIndex = commaIndex + 1;
        }
    }
}
