package bugHunter;

import java.util.ArrayList;

public class BugManager {
	private final ArrayList<Bug> bugs = new ArrayList<>();
	public BugManager() {
		loadDefaultBugs();
    }

    private void loadDefaultBugs() {
        bugs.add(new Bug("NullPointerException",
                "Occurs when using a reference that points to null. Fix: check for null before use and initialize objects properly."));
        bugs.add(new Bug("StackOverflow",
                "Occurs when the call stack exceeds its limit, usually from unbounded recursion. Fix: add a proper base case or convert to iteration."));
        bugs.add(new Bug("IndexOutOfBounds",
                "Occurs when accessing an array or list with an invalid index. Fix: validate indices against the collection's size before accessing."));
        bugs.add(new Bug("Deadlock",
                "Occurs when two or more threads wait on each other forever. Fix: acquire locks in a consistent order or use timeouts."));
        bugs.add(new Bug("Memory Leak",
                "Occurs when objects are no longer needed but remain referenced, preventing garbage collection. Fix: release references and close resources when done."));
        bugs.add(new Bug("Segfault",
                "Occurs when a program accesses memory it does not own. Fix: check pointers and array bounds, and avoid using freed memory."));
        bugs.add(new Bug("Infinite Loop",
                "Occurs when a loop's exit condition is never met. Fix: verify the loop condition changes each iteration and will eventually terminate."));
        bugs.add(new Bug("Syntax Error",
                "Occurs when code violates the language's grammar rules. Fix: read the compiler error message and correct the offending line."));
        bugs.add(new Bug("Divide by Zero",
                "Occurs when a number is divided by zero. Fix: check the divisor before dividing and handle the zero case explicitly."));
    }

    public ArrayList<Bug> getAllBugs() {
        return bugs;
    }

    public int getBugCount() {
        return bugs.size();
    }

    public Bug findByName(String name) {
        if (name == null) {
            return null;
        }
        for (Bug bug : bugs) {
            if (bug.getName().equalsIgnoreCase(name)) {
                return bug;
            }
        }
        return null;
    }

    public boolean nameExists(String name) {
        return findByName(name) != null;
    }
    
    public void updateDiagnosis(String name, String diagnosis) {

        Bug bug = findByName(name);

        if (bug != null) {
            bug.setDiagnosis(diagnosis);
        }

    }
    public void addBug(Bug bug) {
    	if (bug != null) {
    	    bugs.add(bug);
    	}
    }
	
}
