class UserCreateDto {
    constructor (public name: string, public username: string, public email: string, public password: string, public address: string) {

    }
}

export default UserCreateDto;