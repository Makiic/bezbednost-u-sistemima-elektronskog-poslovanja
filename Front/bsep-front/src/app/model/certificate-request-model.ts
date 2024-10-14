import { User } from "./user-model";

export interface CertificateRequest {
  subjectData: User;
  issuerData: User;
}
