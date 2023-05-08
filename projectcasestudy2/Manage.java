package projectcasestudy2;

public interface Manage<E> {
    E creat();
    E update();
    E delete();
    E getById();
    void displayAll();
}
