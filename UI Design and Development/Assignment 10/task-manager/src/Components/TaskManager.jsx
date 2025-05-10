import React, { useState } from "react";
import "./task.css";

// Initial structure for task categories
const initialTasks = {
  new: [],         
  inProgress: [], 
  completed: [],   
};

const TaskManager = () => {
  // State to manage tasks
  const [tasks, setTasks] = useState(initialTasks);

  // modal visibility
  const [isModalOpen, setIsModalOpen] = useState(false);

  // edited or created
  const [currentTask, setCurrentTask] = useState(null);

  // dragged for reordering
  const [draggedTask, setDraggedTask] = useState(null);

  // open the modal for adding or editing a task
  const openModal = (task = null) => {
    setCurrentTask(task);  
    setIsModalOpen(true);   
  };

  // close the modal
  const closeModal = () => {
    setCurrentTask(null);  
    setIsModalOpen(false);  
  };

  //  save a new task or update an existing one
  const saveTask = (task) => {
    const updatedTasks = { ...tasks };
    
    if (task.id) {
      // Remove the task from its previous category
      Object.keys(updatedTasks).forEach((key) => {
        updatedTasks[key] = updatedTasks[key].filter((t) => t.id !== task.id);
      });

      // Add the task to its new category
      updatedTasks[task.status].push({
        ...task,
        completionDate: task.status === "completed" ? new Date() : null, 
      });
    } else {
      // Create a new task with a unique ID
      const newTask = {
        ...task,
        id: Date.now().toString(),    
        status: "new",                
        creationDate: new Date(),
        completionDate: null,
      };
      updatedTasks.new.push(newTask);
    }

    setTasks(updatedTasks);  
    closeModal();         
  };

  //  delete a task
  const deleteTask = (id, status) => {
    const updated = { ...tasks };
    updated[status] = updated[status].filter((task) => task.id !== id); 
    setTasks(updated); 
  };

  //  starting the drag
  const handleDragStart = (task, status) => {
    setDraggedTask({ ...task, from: status });
  };

  // drop action
  const handleDrop = (targetStatus) => {
    if (!draggedTask) return; 

    const updated = { ...tasks };
    updated[draggedTask.from] = updated[draggedTask.from].filter((t) => t.id !== draggedTask.id); 
    updated[targetStatus].push({ ...draggedTask, status: targetStatus }); 

    setTasks(updated);
    setDraggedTask(null)
  };

  //  dropping (prevent default behavior)
  const allowDrop = (e) => e.preventDefault();

  // color based on priority level
  const getPriorityColor = (priority) => {
    switch (priority) {
      case "High": return "#006d77";   
      case "Medium": return "#83c5be"; 
      case "Low": return "#edf6f9";    
      default: return "gray";         
    }
  };

  return (
    <div className="box-cont">
      <h1>Task Manager</h1>
      
      {/* Button to add a new task */}
      <button className="add-btn" onClick={() => openModal()}>Add Task</button>

      {/* Columns for different task statuses */}
      <div className="columns">
        {["new", "inProgress", "completed"].map((status) => (
          <div
            key={status}
            className="column"
            onDragOver={allowDrop} // Allow drop on this column
            onDrop={() => handleDrop(status)} // Handle drop event
          >
            <h2>{status.replace(/([A-Z])/g, " $1")}</h2>

            {/* Display tasks under each status */}
            {tasks[status].map((task) => (
              <div
                key={task.id}
                className="task"
                draggable={status !== "completed"} 
                onDragStart={() => handleDragStart(task, status)} 
                style={{ backgroundColor: `${getPriorityColor(task.priority)}` }}
              >
                <h3>{task.title}</h3>
                <h6>{new Date(parseInt(task.id)).toLocaleDateString()}</h6> 
                {task.completionDate && <h3>{task.completionDate.toLocaleDateString()}</h3>} 
                <p>{task.description}</p>
                <p className="meta">Priority: {task.priority}</p>

                {/* Actions for editing/deleting tasks (not for completed tasks) */}
                {status !== "completed" && (
                  <div className="actions">
                    <button onClick={() => openModal(task)}>Edit</button>
                    <button onClick={() => deleteTask(task.id, status)}>Delete</button>
                  </div>
                )}
              </div>
            ))}
          </div>
        ))}
      </div>

      {/* Modal for adding or editing tasks */}
      {isModalOpen && (
        <div className="cont-overlay">
          <div className="box-modal">
            <h2>{currentTask ? "Edit Task" : "Create Task"}</h2>
            <form onSubmit={(e) => {
              e.preventDefault();
              const formData = new FormData(e.target);
              const taskData = {
                id: currentTask?.id,
                title: formData.get("title"),
                description: formData.get("description"),
                priority: formData.get("priority"),
                status: currentTask?.status || "new",
                creationDate: currentTask?.creationDate || new Date(),
              };
              saveTask(taskData);
            }}>
              {/* Inputs for task details */}
              <label>Title</label>
              <input name="title" defaultValue={currentTask?.title || ""} required />

              <label>Description</label>
              <textarea name="description" defaultValue={currentTask?.description || ""} required />

              <label>Priority</label>
              <select name="priority" defaultValue={currentTask?.priority || "Medium"}>
                <option value="High">High</option>
                <option value="Medium">Medium</option>
                <option value="Low">Low</option>
              </select>

              {/* Modal actions */}
              <div className="box-modal-actions">
                <button type="submit">Save</button>
                <button type="button" onClick={closeModal}>Cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default TaskManager;