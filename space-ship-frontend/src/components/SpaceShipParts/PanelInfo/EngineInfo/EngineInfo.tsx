import React from "react";
import styles from "./EngineInfo.module.css";

import { EngineProps } from "../../../../model/Engine";

type Props = {
    engineProps: EngineProps
}

const EngineInfo: React.FC<Props> = ({engineProps}) => {
    return(
        <div className={styles.panel_container} >
            <div className={styles.panel_actionTitle} >Engine:</div>
            <div>Status: {engineProps.powerStatus ? "On" : "Off"}</div>
        </div>
    );
};

export default EngineInfo;
