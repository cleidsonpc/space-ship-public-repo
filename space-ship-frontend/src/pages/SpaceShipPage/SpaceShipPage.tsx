import React from "react";
import styles from "./SpaceShipPage.module.css";

import PanelLogMessage from "../../components/SpaceShipParts/PanelLogMessage/PanelLogMessage";

import { PowerPlantProps, PowerPlantInitialProps } from "../../model/PowerPlant";
import { EngineProps, EngineInitialProps } from "../../model/Engine";
import { ShieldProps, ShieldInitialProps } from "../../model/Shield";
import PanelActions from "../../components/SpaceShipParts/PanelActions/PanelActions";
import PanelInfo from "../../components/SpaceShipParts/PanelInfo/PanelInfo";
import PanelButtons from "../../components/SpaceShipParts/PanelButtons/PanelButtons";

const SpaceShipPage: React.FC = () => {
    const [logMessage, setLogMessage] = React.useState<any>("");
    const [powerPlantProps, setPowerPlantProps] = React.useState<PowerPlantProps>( PowerPlantInitialProps );
    const [engineProps, setEngineProps] = React.useState<EngineProps>( EngineInitialProps );
    const [shieldProps, setShieldProps] = React.useState<ShieldProps>( ShieldInitialProps );

    const [openPanelInfoDisplay, setOpenPanelInfoDisplay] = React.useState<string>("");

    const buildLogMessage = async (param:string) => {
        setLogMessage(logMessage + " | " + param);
    }

    const fetchShipStatus = async () => {
//         console.log("SpaceShipPage fetchShipStatus");
        fetch('http://localhost:8080/ship_status')
            .then((response) => response.json())
            .then((data) => {
                setShieldProps(data.shieldControllerDto);
                setEngineProps(data.engineControllerDto);
                setPowerPlantProps(data.powerPlantControllerDto);
            }).catch((err) => {
                console.log(err.message);
            });
    };

    React.useEffect(() => {
        // TODO - Find out a way to refresh data when changed, not refreshing every second.
        const interval = setInterval(() => {
          fetchShipStatus();
        }, 1000);

        return () => clearInterval(interval);
    }, []);



//         then((response) => response.json())
//               .then((data) => {
//                  setPosts((posts) => [data, ...posts]);
//                  setTitle('');
//                  setBody('');
//               })

//         if (response.status === 200) {
//           setPosts(
//              posts.filter((post) => {
//                 return post.id !== id;
//              })
//           );
//        } else {
//           return;
//        }


    const calcEngineEnergyAvailable = () => {
        if(engineProps.powerStatus) {
            powerPlantProps.energyAvailable -= engineProps.powerConsumption;
        } else if(powerPlantProps.powerStatus && !engineProps.powerStatus) {
            powerPlantProps.energyAvailable += engineProps.powerConsumption;
        }
        setPowerPlantProps(powerPlantProps);
    }

    const calcEnergyAvailable = () => {
        if(shieldProps.powerStatus) {
            powerPlantProps.energyAvailable -= shieldProps.powerConsumption;
        } else if(powerPlantProps.powerStatus && !shieldProps.powerStatus) {
            powerPlantProps.energyAvailable += shieldProps.powerConsumption;
        }
        setPowerPlantProps(powerPlantProps);
    }

    const backgroundEngineOnOff = () => {
        let result;

        if(engineProps.powerStatus) {
            result = styles.sp_backgroundEngineOn;
        } else {
            result = styles.sp_backgroundEngineOff;
        }

        return result;
    }

    return(
        <div className={styles.sp_container}>
            <div className={styles.sp_pageTitle} >Title</div>

            <div className={styles.sp_background + " " + backgroundEngineOnOff()} >
                <div className={styles.sp_shipPanelContainer} >
                    { engineProps.powerStatus ? "engineEntity ON" : "" }

                    <div className={styles.sp_shipPanel} >
                        <div className={styles.sp_shipPanelLeft} >
                            <PanelActions
                                setInfoDisplay={setOpenPanelInfoDisplay}
                                powerPlantProps={powerPlantProps}
                                engineProps={engineProps}
                                shieldProps={shieldProps} />
                        </div>

                        <div className={styles.sp_shipPanelMiddle} >
                            <PanelInfo openPanelInfoDisplay={openPanelInfoDisplay}
                                powerPlantProps={powerPlantProps}
                                engineProps={engineProps}
                                shieldProps={shieldProps} />
                        </div>

                        <div className={styles.sp_shipPanelRight} >
                            <div className={styles.sp_shipPanelRightUp} >
                                <PanelLogMessage message={logMessage} />
                            </div>

                            <div className={styles.sp_shipPanelRightDown} >
                                <PanelButtons
                                    powerPlantProps={powerPlantProps}
                                    engineProps={engineProps}
                                    shieldProps={shieldProps} />
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div className={styles.sp_pageFooter} >
                Footer
            </div>
        </div>
    );
};

export default SpaceShipPage;
