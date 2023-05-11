package projectcasestudy2.io;

public interface Manage<E> {
    E creat();
    E update();
    E delete();
    E getById();
    void displayAll();
}
