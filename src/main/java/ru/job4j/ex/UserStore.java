package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User user = new User("", false);
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(login)) {
                user = users[i];
            } else {
                throw new UserNotFoundException("The user is not found");
            }
        }
        return user;
    }

    public static boolean validate(User user) throws UserInvalidException {
        boolean rsl;
        if (user.isValid() && user.getUsername().length() > 2) {
            rsl = true;
        } else {
            throw new UserInvalidException("The user is not valid");
        }
        return rsl;
    }

    public static void main(String[] args) {
        try {
            User[] users = {
                    new User("Petr Arsentev", true)
            };
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ui) {
            ui.printStackTrace();
        } catch (UserNotFoundException unf) {
            unf.printStackTrace();
        }
    }
}
