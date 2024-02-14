import React from "react";
import styles from "./InputCheckbox.module.css";

type Props = {
    checked?:boolean;
    onChange(param:boolean):void;
}

const InputCheckbox: React.FC<Props> = ({onChange, checked}) => {

    const getSwitchPositionClass = () => {
        let result;

        if(checked) {
            result = styles.inputCheckbox_switchOn;
        } else {
            result = styles.inputCheckbox_switchOff;
        }

        return result;
    }

    const getLightImageStatusClass = () => {
        let result;

        if(checked) {
            result = styles.inputCheckbox_lightOn;
        } else {
            result = styles.inputCheckbox_lightOff;
        }

        return result;
    }

    return(
        <div className={styles.inputCheckbox_container}>
            <div className={styles.inputCheckbox_light + " " + getLightImageStatusClass()} ></div>
            <button className={styles.inputCheckbox_switch + " " + getSwitchPositionClass()} onClick={() => {onChange(!checked)}} />
        </div>
    )
}

export default InputCheckbox;
