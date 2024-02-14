import React from "react";
import styles from "./PowerPlantInfo.module.css";

import { PowerPlantProps } from "../../../../model/PowerPlant";

type Props = {
    powerPlantProps:PowerPlantProps
}

const PowerPlantInfo: React.FC<Props> = ({powerPlantProps}) => {
    return(
        <div className={styles.panel_container} >
            <div className={styles.painel_actionTitle} >PowerPlant:</div>
            <div>Status:{powerPlantProps.powerStatus ? "On" : "Off"}</div>
            <div>Energy Available: {powerPlantProps.energyAvailable}</div>
        </div>
    );
};

export default PowerPlantInfo;
