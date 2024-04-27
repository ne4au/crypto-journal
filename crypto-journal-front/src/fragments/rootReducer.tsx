import { combineReducers } from 'redux';
import signingFormReducer from './singing/formSlice';


const rootReducer = combineReducers({
    formState: signingFormReducer
});

export type RootState = ReturnType<typeof rootReducer>;

export {
    rootReducer,
}