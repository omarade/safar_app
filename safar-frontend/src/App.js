import UserService from "./services/UserService";

function App() {

  function getStudents(){
    UserService.getUsers()
    // .then( (res) => {
    //   let users = res.data
    //   return users;
    // })
  }
  

  return (
    <div className="App">
        {getStudents()}
    </div>
  );
}

export default App;
