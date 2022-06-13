import { BrowserRouter as Router, Route, Routes, Fragment } from 'react-router-dom'
import UserService from "./services/UserService.ts";
import LoginForm from "./components/forms/LoginForm.tsx";
import RegistrationForm from "./components/forms/RegistrationForm.tsx";
function App() {

	function getUsers(){
		UserService.getUsers()
		// .then( (res) => {
		//   let users = res.data
		//   return users;
		// })
	}
	

	return (
		// <div className="App">
		//     {getUsers()}
		// </div>
		<Router>

			<Routes>
			
				<Route path="/login" element={<LoginForm />} />
				<Route path="/register" element={<RegistrationForm />} />
			
			</Routes>
			
		</Router>
		
	);
}

export default App;
