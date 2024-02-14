import React from "react";
import style from "./App.module.css";

import SpaceShipPage from "./pages/SpaceShipPage/SpaceShipPage";
import ErrorPage from "./pages/ErrorPage/ErrorPage";

function App() {

    const [isHealthy, setIsHealthy] = React.useState<boolean>(false);

    let page;
    fetch('http://localhost:8080/health/check')
        .then((response) => response.json())
        .then((data) => {
            setIsHealthy(data.status);
        }).catch((err) => {
            console.log("backend unhealthy.");
            console.log(err.message);
        });

    if(isHealthy) {
        page = <SpaceShipPage />
    } else {
        page = <ErrorPage />
    }

    return (
        <div className={style.AppStyle}>
          {page}
        </div>
    );
}

export default App;
