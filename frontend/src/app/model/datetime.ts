export interface Date {
    year: number
    month: number
    day: number
}

export interface Time {
    hour: number
    minute: number
    second: number
    nano: number
}

export interface DateTime {
    date: Date
    time: Time
}
