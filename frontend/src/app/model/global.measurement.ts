import { DateTime } from './datetime'

export interface GlobalMeasurement {
    timestamp: DateTime
    currentInstantaneousPowerUsed: number
    powerOverHours: number[]
    totalCost: number
    temperature: number
    humidity: number
    smartMeterStatus: boolean
    dhtStatus: boolean
    temperatureTrend: number
    humidityTrend: number
}
