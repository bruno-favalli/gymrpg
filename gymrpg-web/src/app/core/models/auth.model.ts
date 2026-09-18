export interface RegisterRequest{
  name: string;
  email: string;
  password: string;
}

export interface LoginRequest{
  email: string;
  password: string;
}

export interface UserResponse{
  id: number;
  name: string;
  email: string;
  createdAt: string;
}

export interface LoginResponse{
  token: string;
  user: UserResponse;
}
