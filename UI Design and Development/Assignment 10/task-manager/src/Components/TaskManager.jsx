import React, { useState } from "react";
import "./task.css";


const initialTasks = {
  new: [],         
  inProgress: [], 
  completed: [],   
};

const TaskManager = () => {
 
  const [tasks, setTasks] = useState(initialTasks);

  
  const [isModalOpen, setIsModalOpen] = useState(false);

 
  const [currentTask, setCurrentTask] = useState(null);

  
  const [draggedTask, setDraggedTask] = useState(null);

  
  const openModal = (task = null) => {
    setCurrentTask(task);  
    setIsModalOpen(true);   
      };


  const closeModal = () => {
    setCurrentTask(null);  
    setIsModalOpen(false);  
  };

 
  const saveTask = (task) => {
    const updatedTasks = { ...tasks };
  
    if (task.id) {
      Object.keys(updatedTasks).forEach((key) => {
        updatedTasks[key] = updatedTasks[key].filter((t) => t.id !== task.id);
      });
  
      updatedTasks[task.status].push({
        ...task,
        completionDate: task.status === "completed" ? new Date().toISOString() : null, 
      });
    } else {
      const newTask = {
        ...task,
        id: Date.now().toString(),
        status: "new",
        creationDate: new Date().toISOString(),
        completionDate: null,
      };
      updatedTasks.new.push(newTask);
    }
  
    setTasks(updatedTasks);
    closeModal();
  };

 
  const deleteTask = (id, status) => {
    const updated = { ...tasks };
    updated[status] = updated[status].filter((task) => task.id !== id); 
    setTasks(updated); 
  };

 
  const handleDragStart = (task, status) => {
    setDraggedTask({ ...task, from: status });
  };

  
  const handleDrop = (targetStatus) => {
    if (!draggedTask) return; 

    const updated = { ...tasks };
    updated[draggedTask.from] = updated[draggedTask.from].filter((t) => t.id !== draggedTask.id); 
    updated[targetStatus].push({ ...draggedTask, status: targetStatus }); 

    setTasks(updated);
    setDraggedTask(null)
  };


  const allowDrop = (e) => e.preventDefault();

  
 
  const getPriorityColor = (priority) => {
    switch (priority) {
      case "High": return "#FF6B6B"; // Light red for high priority
      case "Medium": return "#4ECDC4"; // Aqua for medium priority
      case "Low": return "#5E60CE"; // Blue for low priority
      default: return "#6C757D"; // Neutral gray for undefined priority
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
            onDragOver={allowDrop} 
            onDrop={() => handleDrop(status)} 
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
                <h6>Created on: {new Date(parseInt(task.id)).toLocaleDateString()}</h6> 
                {task.completionDate && (
  <h6>Completed on: {new Date(task.completionDate).toLocaleString()}</h6>
)}

                <p>{task.description}</p>
                <p className="meta">Priority: {task.priority}</p>

                
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