import React from "react";
import styles from "./PanelLogMessage.module.css";

type LogMessageProps = {
    message:string
};

const LogMessage: React.FC<LogMessageProps> = ({message}) => {
    return (
        <div className={styles.lm_container} >
            <div>Log Message</div>
            <div className={styles.lm_message} >{message}</div>
        </div>
    );
}

export default LogMessage;
