export interface ShieldProps {
    powerStatus:boolean,
    powerConsumption:number,
    capacity:number
}

export const ShieldInitialProps = {
    powerStatus: false,
    capacity: 0,
    powerConsumption: 0
}
