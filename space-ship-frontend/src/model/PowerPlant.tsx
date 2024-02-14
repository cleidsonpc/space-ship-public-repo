export interface PowerPlantProps {
    powerStatus:boolean;
    energyAvailable:number;
}

export const PowerPlantInitialProps = {
    powerStatus: false,
    energyAvailable: 0,
    energyMax: 0
}
