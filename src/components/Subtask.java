package components;

public class Subtask extends Task {
    private final Integer idEpic;

    public Subtask(String taskName, String taskDescription, Integer idEpic) {
        super(taskName, taskDescription);
        this.idEpic = idEpic;
    }

    public Integer getIdEpic() {
        return idEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "taskName='" + getTaskName() + '\'' +
                ", taskDescription='" + getTaskDescription() + '\'' +
                ", status=" + getStatus() +
                ", id=" + getId() +
                ", idEpic=" + getIdEpic() +
                '}' + '\n';
    }
}

