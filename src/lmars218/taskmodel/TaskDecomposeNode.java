package lmars218.taskmodel;

public class TaskDecomposeNode {
    enum ImplementType {
        NONE, SIMPLE, COMPLEX
    }

    private ImplementType implementType;
    private AbstractTask task;

    private ImplemtTask implement;

    public ImplemtTask getImplement() {
        return implement;
    }

    public void setImplement(ImplemtTask implement) {
        this.implement = implement;
    }

    public ImplementType getImplementType() {
        return implementType;
    }

    public void setImplementType(ImplementType implementType) {
        this.implementType = implementType;
    }

    public TaskDecomposeNode(AbstractTask task) {
        this.task = task;
        this.implementType = ImplementType.NONE;
        this.implement = null;
    }

    @Override
    public String toString() {
        return task.toString() + "[" + implementType + "]";
    }

    public AbstractTask getTask() {
        return task;
    }

    public void setTask(AbstractTask task) {
        this.task = task;
    }

}
