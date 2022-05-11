export class User {

  public id: number;
  public username: string;
  public balance: number

  constructor(id: number, username: string, balance: number) {
    this.id = id;
    this.username = username;
    this.balance = balance;
  }
}
