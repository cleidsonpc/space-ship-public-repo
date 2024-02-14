import React from "react";
//import styles from "./PanelActions.module.css";

import { PowerPlantProps, PowerPlantInitialProps } from "../../../model/PowerPlant";
import { EngineProps, EngineInitialProps } from "../../../model/Engine";
import { ShieldProps, ShieldInitialProps } from "../../../model/Shield";

type Props = {
    setInfoDisplay(inputParam:string): void,
    powerPlantProps: PowerPlantProps,
    engineProps: EngineProps,
    shieldProps: ShieldProps
}

const Actions: React.FC<Props> = ({setInfoDisplay, powerPlantProps, engineProps, shieldProps}) => {
    return (<div>
        ... Panel Actions Buttons ...
        <button onClick={() => {setInfoDisplay("powerPlantInfo")}} >PowerPlant</button>
        <button onClick={() => {setInfoDisplay("engineInfo")}} >Engine</button>
        <button onClick={() => {setInfoDisplay("shieldInfo")}} >Shield</button>
    </div>);
}

export default Actions;