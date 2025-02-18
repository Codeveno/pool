


public class SocialNetwork {

    public static void main(String[] args) {
        String network = "";

        // Starting network
        System.out.println(network); // Print the current state of the network

        // Add users
        network = addUser(network, "swofferh", "achen47-hibbahk"); // Add user swofferh with friends achen47 and hibbahk
        network = addUser(network, "achen47", "swofferh"); // Add user achen47 with friend swofferh
        network = addUser(network, "hibbahk", "swofferh"); // Add user hibbahk with friend swofferh

        // Print network and size
        System.out.println(network);
        System.out.println(networkSize(network)); // Print the size of the network

        // Remove user
        network = removeUser(network, "achen47");
        System.out.println(network);
        System.out.println(networkSize(network));

        // Add more users
        network = addUser(network, "juliak24", "hibbahk"); // Add user juliak24 with friend hibbahk
        System.out.println(network);
        System.out.println(networkSize(network));

        network = addUser(network, "meravf", "swofferh-juliak24"); // Add user meravf with friends swofferh and juliak24
        System.out.println(network);
        System.out.println(networkSize(network));

        network = addUser(network, "mxw", ""); // Add user mxw with no friends
        System.out.println(network);
        System.out.println(networkSize(network));

        // Visualize network
        visualizeNetwork(network);
    }

    public static String addUser(String network, String user, String friends) {
        if (!friends.isEmpty()) {
            return network + user + ":" + friends + ",";
        } else {
            return network + user + ":,";
        }
    }

    public static int networkSize(String network) {
        int count = 0;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ':') {
                count++;
            }
        }
        return count;
    }

    public static String removeUser(String network, String user) {
        int start = network.indexOf(user + ":");
        if (start == -1) return network;
        int end = network.indexOf(",", start) + 1;
        String updated = network.substring(0, start) + network.substring(end);

        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < updated.length()) {
            int colon = updated.indexOf(":", i);
            if (colon == -1) break;
            String currUser = updated.substring(i, colon);
            int comma = updated.indexOf(",", colon);
            String friends = updated.substring(colon + 1, comma);

            if (friends.contains(user)) {
                friends = friends.replace("-" + user, "").replace(user + "-", "").replace(user, "");
            }

            result.append(currUser).append(":").append(friends).append(",");
            i = comma + 1;
        }

        return result.toString();
    }

    public static void visualizeNetwork(String network) {
        int i = 0;
        while (i < network.length()) {
            int colon = network.indexOf(":", i);
            if (colon == -1) break;
            String user = network.substring(i, colon);
            int comma = network.indexOf(",", colon);
            String friends = network.substring(colon + 1, comma);

            System.out.println("User: " + user);
            if (!friends.isEmpty()) {
                System.out.println("Friends:");
                int start = 0;
                while (start < friends.length()) {
                    int dash = friends.indexOf("-", start);
                    if (dash == -1) {
                        System.out.println("  " + friends.substring(start));
                        break;
                    } else {
                        System.out.println("  " + friends.substring(start, dash));
                        start = dash + 1;
                    }
                }
            } else {
                System.out.println("No friends");
            }
            System.out.println();

            i = comma + 1;
        }
    }
}
