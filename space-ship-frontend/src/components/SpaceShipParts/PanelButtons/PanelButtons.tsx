import React from "react";
import styles from "./PanelButtons.module.css";

import InputCheckbox from "../InputCheckbox/InputCheckbox";
import { EngineProps } from "../../../model/Engine";
import { PowerPlantProps } from "../../../model/PowerPlant";
import { ShieldProps } from "../../../model/Shield";

type Props = {
    engineProps: EngineProps,
    powerPlantProps: PowerPlantProps,
    shieldProps: ShieldProps
}

const PanelButtons: React.FC<Props> = ({engineProps, powerPlantProps, shieldProps}) => {

    // POWER PLANT
    const onChangePowerPlantOnOff = (inputParam:boolean) => {
        if(!inputParam) {
            onChangeEngineOnOff(false);
            onChangeShieldOnOff(false);
        }

        if(inputParam) {
            powerPlantOn();
        } else {
            powerPlantOff();
        }
    }

    const powerPlantOn = () => {
        // Simple PUT request with a JSON body using fetch
        let serviceRequest = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify({ "powerStatus": true})
        };
        powerPlantUpdate(serviceRequest);
    }

    const powerPlantOff = () => {
        // Simple PUT request with a JSON body using fetch
        let serviceRequest = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify({ "powerStatus": false})
        };
        powerPlantUpdate(serviceRequest);
    }

    const powerPlantUpdate = (inputParam:any) => {
        fetch('http://localhost:8080/power_plant/update', inputParam)
            .then((response) => response.json())
            .then((data) => {
//                 console.log("Service return: " + JSON.stringify(data));
//                 powerPlantProps.powerStatus = data.poserStatus;
//                 powerPlantProps.energyAvailable = data.energyAvailable;
            }).catch((err) => {
                console.log(err.message);
            });
    }

    // ENGINE
    const onChangeEngineOnOff = (inputParam:boolean) => {
        if(!powerPlantProps.powerStatus) {
            inputParam = false;
        }

        if(inputParam) {
            engineOn();
        } else {
            engineOff();
        }
    }

    const engineOn = () => {
        // Simple PUT request with a JSON body using fetch
        let serviceRequest = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify({ "powerStatus": true})
        };
        engineUpdate(serviceRequest);
    }

    const engineOff = () => {
        // Simple PUT request with a JSON body using fetch
        let serviceRequest = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify({ "powerStatus": false})
        };
        engineUpdate(serviceRequest);
    }

    const engineUpdate = (inputParam:any) => {
        fetch('http://localhost:8080/engine/update', inputParam)
            .then((response) => response.json())
            .then((data) => {
//                 console.log("Service return: " + JSON.stringify(data));
//                 powerPlantProps.powerStatus = data.poserStatus;
//                 powerPlantProps.energyAvailable = data.energyAvailable;
            }).catch((err) => {
                console.log(err.message);
            });
    }

    // SHIELD
    const onChangeShieldOnOff = (inputParam:boolean) => {
        if(!powerPlantProps.powerStatus) {
            inputParam = false;
        }
        if(inputParam) {
            shieldOn();
        } else {
            shieldOff();
        }
//         shieldProps.powerStatus = inputParam;
    }

    const shieldOn = () => {
        // Simple PUT request with a JSON body using fetch
        let serviceRequest = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify({ "powerStatus": true })
        };
        shieldUpdate(serviceRequest);
    }

    const shieldOff = () => {
        // Simple PUT request with a JSON body using fetch
        let serviceRequest = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify({ "powerStatus": false })
        };
        shieldUpdate(serviceRequest);
    }

    const shieldUpdate = (inputParam:any) => {
        fetch('http://localhost:8080/shield/update', inputParam)
            .then((response) => response.json())
            .then((data) => {
//                 console.log("SUCCESS");
            }).catch((err) => {
                console.log(err.message);
            });
    }

    return(
        <div className={styles.power_button_container} >
            <div className={styles.power_button_leftContainer} >...</div>

            <div className={styles.power_button_rightContainer} >
                <div className={styles.power_button_switch} >
                    <InputCheckbox checked={powerPlantProps.powerStatus} onChange={onChangePowerPlantOnOff}/>
                </div>

                <div className={styles.power_button_switch} >
                    <InputCheckbox checked={engineProps.powerStatus} onChange={onChangeEngineOnOff} />
                </div>

                <div className={styles.power_button_switch} >
                    <InputCheckbox checked={shieldProps.powerStatus} onChange={onChangeShieldOnOff} />
                </div>
            </div>
        </div>
    );
}

export default PanelButtons;
