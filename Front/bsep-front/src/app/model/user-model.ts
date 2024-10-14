import { UserRole } from "./user-role-enum";

export interface User{
    id?: number;
    userRole: UserRole;
    email: string;
    password: string;
    x500_CN: string;
    x500_SURNAME: string;
    x500_GIVENNAME: string;
    x500_O: string;
    x500_OU: string;
    x500_C: string;
    x500_E: string;
    x500_UID: string;
    isSubject: boolean; // Property indicating whether the user is a subject
    isIssuer: boolean;
    serialNumber: string;
    startDate: string;
    endDate: string;
}