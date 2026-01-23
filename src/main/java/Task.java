public abstract class Task {
    /** Name of the Task */
    private String taskName;

    /** Boolean status of the task */
    private boolean status;

    /** Task type of the task */
    private TaskType type;

    /**
     * Enum to represents the type of the task
     * Tag to display in the String Representation of Task
     */
    enum TaskType {
        TODO("T"),
        DEADLINE("D"),
        EVENT("E");

        /** Tag to show the Task type */
        private String tag;

        /**
         * Constructor for Task type
         * @param tag Tag used for String representation of Task
         */
        TaskType(String tag) {
            this.tag = tag;
        }

        /**
         * Returns the String representation of the Tag
         * @return String representation of the task type
         */
        public String tag() {
            return tag;
        }
    }


    /**
     * Constructor for the task
     *
     * @param taskName Name of the task
     */
    public Task(TaskType type, String taskName) {
        this.type = type;
        this.taskName = taskName;
        this.status = false;
    }

    /**
     * Mark the task as complete
     */
    public void complete() {
        this.status = true;
    }

    /**
     * Mark the task as incomplete
     */
    public void undo() {
        this.status = false;
    }

    /**
     * String representation of the task
     *
     * @return A string representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type.tag(),
                this.status ? "X" : " ", taskName);
    }
}
