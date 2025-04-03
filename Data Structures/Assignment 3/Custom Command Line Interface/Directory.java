import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String name;
    private String dateTime;
    private Directory parent;
    private List<Directory> children;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.dateTime = LocalDateTime.now().toString();
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public List<Directory> getChildren() {
        return children;
    }

    public String getDateTime() {
        return dateTime;
    }

    public boolean addDirectory(Directory newDir) {
        for (Directory dir : children) {
            if (dir.getName().equals(newDir.getName())) {
                return false;
            }
        }
        children.add(newDir);
        return true;
    }

    public Directory findDirectory(String name) {
        if (this.name.equals(name)) return this;
        for (Directory child : children) {
            Directory found = child.findDirectory(name);
            if (found != null) return found;
        }
        return null;
    }
}