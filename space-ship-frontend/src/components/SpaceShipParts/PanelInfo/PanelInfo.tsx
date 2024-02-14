import React from "react";
import styles from "./PanelInfo.module.css";

import PowerPlantInfo from "./PowerPlantInfo/PowerPlantInfo";
import { PowerPlantProps, PowerPlantInitialProps } from "../../../model/PowerPlant";

import EngineInfo from "./EngineInfo/EngineInfo";
import { EngineProps, EngineInitialProps } from "../../../model/Engine";

import ShieldInfo from "./ShieldInfo/ShieldInfo";
import { ShieldProps, ShieldInitialProps } from "../../../model/Shield";

type Props = {
    openPanelInfoDisplay:string,
    engineProps: EngineProps,
    powerPlantProps: PowerPlantProps,
    shieldProps: ShieldProps
}

const PanelInfo: React.FC<Props> = ({openPanelInfoDisplay, engineProps, powerPlantProps, shieldProps}) => {

    const [infoPanel, setInfoPanel] = React.useState<any>();

    React.useEffect(() => {
        console.log(openPanelInfoDisplay);
        if(openPanelInfoDisplay === "powerPlantInfo") {
            setInfoPanel(<PowerPlantInfo powerPlantProps={powerPlantProps} />);
        } else if(openPanelInfoDisplay === "engineInfo") {
            setInfoPanel(<EngineInfo engineProps={engineProps} />);
        } else if(openPanelInfoDisplay === "shieldInfo") {
            setInfoPanel(<ShieldInfo shieldProps={shieldProps} />);
        } else {
            setInfoPanel(null);
        }
    }, [openPanelInfoDisplay]);

    return(
        <div className={styles.pi_container} >
            {infoPanel}
        </div>
    );
}

export default PanelInfo;
