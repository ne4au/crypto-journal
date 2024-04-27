import { FC } from 'react';

interface Props {
    buttonText: string,
}

const SigningButton: FC<Props> = ({
    buttonText,
}) => (
    <button type="button" className={`py-4 px-6 font-poppins font-medium text-[16px] text-primary rounded-[10px]
     outline-none bg-blue-gradient hover:underline hover:scale-105  underline-offset-4`}>
        {buttonText}
    </button>
);

export default SigningButton;