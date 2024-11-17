public interface MutableUser extends ReadUser {
    void setEmail(String email);
    void setPassword(String password);
    void setUserType(int userType);
}
